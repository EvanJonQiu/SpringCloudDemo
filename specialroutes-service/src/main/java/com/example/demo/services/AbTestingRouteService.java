package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.NoRouteFound;
import com.example.demo.model.AbTestingRoute;
import com.example.demo.repository.AbTestingRouteRepository;

@Service
public class AbTestingRouteService {
	@Autowired
    private AbTestingRouteRepository abTestingRouteRepository;
	
	public AbTestingRoute getRoute(String serviceName) {
        AbTestingRoute route = abTestingRouteRepository.findByServiceName(serviceName);

        if (route==null){
            throw new NoRouteFound();
        }

        return route;
    }

    public void saveAbTestingRoute(AbTestingRoute route){

        abTestingRouteRepository.save(route);

    }

    public void updateRouteAbTestingRoute(AbTestingRoute route){
        abTestingRouteRepository.save(route);
    }

    public void deleteRoute(AbTestingRoute route){
        abTestingRouteRepository.deleteById(route.getServiceName());
    }
}
