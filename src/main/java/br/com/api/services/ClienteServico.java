package br.com.api.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.dtos.ClienteDTO;
import br.com.api.entities.Cliente;
import br.com.api.exceptions.ClienteNotFoundException;
import br.com.api.repositories.ClienteRepository;
import br.com.api.templateRest.IpLocalTemplate;
import br.com.api.templateRest.LocalidadesTemplate;
import br.com.api.templateRest.WeatherDataTemplate;
import br.com.api.templateRest.WeatherTemplate;

@Service
public class ClienteServico {
	@Autowired
	ClienteRepository clienteRepo;
	@Autowired
    private ModelMapper modelMapper;
	
	@Value("${latlong_by_ip}")
	private String latlongByIp;
	@Value("${loc_by_latlong}")
	private String locByLatlong;
	@Value("${weather_by_woeid}")
	private String weatherByWoeid;
	
	public ClienteDTO save(ClienteDTO clienteDTO) {
		Cliente cliente =  new Cliente();
		if(clienteDTO.getId() != null) {
			cliente.setId(clienteDTO.getId());
			String nome = clienteDTO.getNome();
			Integer idade = clienteDTO.getIdade();
			Long id = clienteDTO.getId();
			clienteRepo.findById(clienteDTO.getId()).
			map(c -> {
				c.setNome(nome);
				c.setIdade(idade);
				return clienteRepo.save(c);
			}).orElseThrow(() -> new ClienteNotFoundException(id));
		}else {
		
			clienteDTO = criarCliente(clienteDTO);
			cliente = clienteRepo.save(modelMapper.map(clienteDTO, Cliente.class));
		}
		Optional<Cliente> c  = clienteRepo.findById(cliente.getId());
		//clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
		if(!c.isPresent()) {
			
		}
		return modelMapper.map(c.get(), ClienteDTO.class);
	}
	
	private ClienteDTO criarCliente(ClienteDTO clienteDTO) {
		RestTemplate restTemplate = new RestTemplate();
		 
		IpLocalTemplate ipLocalizacao = restTemplate.getForObject(latlongByIp+clienteDTO.getRequestCreate().getIpOrigem(), IpLocalTemplate.class);
		 
		clienteDTO.getRequestCreate().setLatitude(Double.valueOf(ipLocalizacao.getData().getLatitude()));
		clienteDTO.getRequestCreate().setLongitude((Double.valueOf(ipLocalizacao.getData().getLongitude())));
		 
		LocalidadesTemplate[] localidades = restTemplate.getForObject(locByLatlong+clienteDTO.getLattlong(), LocalidadesTemplate[].class);
		//Lista Ordenada pela distancia da latitude e longitude e com 
		List<LocalidadesTemplate> lista = Arrays.asList(localidades).stream().
		filter(x->x.getWoeid()!=null && !"".equals(x.getWoeid())).
		sorted((l1,l2)->Long.compare(Long.parseLong(l1.getDistance()), Long.parseLong(l2.getDistance()))).
		collect(Collectors.toList());
		boolean achou =  false;
		int i = 0;
		while(!achou) {
			WeatherTemplate local = restTemplate.getForObject(weatherByWoeid+lista.get(i).getWoeid(), WeatherTemplate.class);
			Optional<WeatherDataTemplate> w = local.getConsolidated_weather().stream().
			filter(x->x.getMax_temp()!=null && x.getMin_temp()!=null).
			findFirst();
			if(w.isPresent()) {
				clienteDTO.getRequestCreate().setMaxima(Float.parseFloat(w.get().getMax_temp()));
				clienteDTO.getRequestCreate().setMinima(Float.parseFloat(w.get().getMin_temp()));
				achou = true;
			}
		}
		return clienteDTO;
	}
	
	public ClienteDTO findById(Long id) {
		Cliente cliente = clienteRepo.findById(id).
				orElseThrow(() -> new ClienteNotFoundException(id));
		return  modelMapper.map(cliente, ClienteDTO.class);
	}
	
	public Iterable<ClienteDTO> findAll() {
		List<ClienteDTO> lista = new ArrayList<ClienteDTO>();
		clienteRepo.findAll().forEach(c->{lista.add(modelMapper.map(c,ClienteDTO.class));});;
		return lista ;
	}
	
	public void deleteById(Long id) {
		Cliente entity = new Cliente();
		entity.setId(id);
		clienteRepo.delete(entity);
	}
}
