package br.com.api.services;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.api.dtos.ClienteDTO;
import br.com.api.entities.Cliente;
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
	
	public ClienteDTO save(ClienteDTO clienteDTO) {
		 RestTemplate restTemplate = new RestTemplate();
		 
		 IpLocalTemplate ipLocalizacao = restTemplate.getForObject("https://ipvigilante.com/json/"+clienteDTO.getRequestCreate().getIpOrigem(), IpLocalTemplate.class);
		 
		 clienteDTO.getRequestCreate().setLatitude(Double.valueOf(ipLocalizacao.getData().getLatitude()));
		 clienteDTO.getRequestCreate().setLongitude((Double.valueOf(ipLocalizacao.getData().getLongitude())));
		 
		 LocalidadesTemplate[] localidades = restTemplate.getForObject("https://www.metaweather.com/api/location/search/?lattlong="+clienteDTO.getLattlong(), LocalidadesTemplate[].class);
		
		 //Lista Ordenada pela distancia da latitude e longitude e com 
		 List<LocalidadesTemplate> lista = Arrays.asList(localidades).stream().
		 filter(x->x.getWoeid()!=null && !"".equals(x.getWoeid())).
		 sorted((l1,l2)->Long.compare(Long.parseLong(l1.getDistance()), Long.parseLong(l2.getDistance()))).
		 collect(Collectors.toList());
		 boolean achou =  false;
		 int i = 0;
		 while(!achou) {
			 WeatherTemplate local = restTemplate.getForObject("https://www.metaweather.com/api/location/"+lista.get(i).getWoeid(), WeatherTemplate.class);
			 Optional<WeatherDataTemplate> w = local.getConsolidated_weather().stream().
					 filter(x->x.getMax_temp()!=null && x.getMin_temp()!=null).
					 findFirst();
			 if(w.isPresent()) {
				 clienteDTO.getRequestCreate().setMaxima(Float.parseFloat(w.get().getMax_temp()));
				 clienteDTO.getRequestCreate().setMinima(Float.parseFloat(w.get().getMin_temp()));
				 achou = true;
			 }
		 }
		 Cliente cliente  = modelMapper.map(clienteDTO, Cliente.class);
		 
		 cliente = clienteRepo.save(cliente);
		 //clienteDTO = modelMapper.map(cliente, ClienteDTO.class);
		return modelMapper.map(cliente, ClienteDTO.class);
	}
}
