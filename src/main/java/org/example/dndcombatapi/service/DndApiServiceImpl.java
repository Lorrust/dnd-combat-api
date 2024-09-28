package org.example.dndcombatapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

@Service
public class DndApiServiceImpl implements DndApiService {

    private final WebClient webClient;

    public DndApiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.open5e.com").build();
    }

    @Override
    public Map<String, Object> getMonster(String monsterName) {
        try {
            return webClient.get()
                    .uri("/v1/monsters/" + monsterName + "/?format=json")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();
        } catch (WebClientResponseException e) {
            System.err.println("Error status code: " + e.getStatusCode());
            System.err.println("Error response body: " + e.getResponseBodyAsString());
            throw new RuntimeException("Error retrieving monster: " + e.getMessage());
        }
    }

    @Override
    public List<String> getMonsterNames(Integer page) {
        try {
            Map<String, Object> response = webClient.get()
                    .uri("v1/monsters/?page=" + page + "&format=json")
                    .retrieve()
                    .bodyToMono(Map.class)
                    .block();

            return ((List<Map<String, String>>) response.get("results")).stream()
                    .map(monster -> monster.get("name"))
                    .toList();
        } catch (WebClientResponseException e) {
            throw new RuntimeException("Error retrieving monster names: " + e.getMessage());
        }
    }

}
