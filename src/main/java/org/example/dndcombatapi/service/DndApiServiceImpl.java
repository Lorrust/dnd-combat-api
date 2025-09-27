package org.example.dndcombatapi.service;

import org.example.dndcombatapi.dto.MonsterCharacterDTO;
import org.example.dndcombatapi.dto.MonsterListDTO;
import org.example.dndcombatapi.dto.MonsterSummaryDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DndApiServiceImpl implements DndApiService {

    private static final Logger logger = LoggerFactory.getLogger(DndApiServiceImpl.class);

    private final WebClient webClient;

    public DndApiServiceImpl(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("https://api.open5e.com")
                .build();
    }

    @Override
    public MonsterCharacterDTO getMonster(String monsterName) {
        try {
            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/monsters/{name}/")
                            .queryParam("format", "json")
                            .build(monsterName))
                    .retrieve()
                    .bodyToMono(MonsterCharacterDTO.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();
        } catch (WebClientResponseException e) {
            logger.error("Error retrieving monster '{}': {}", monsterName, e.getResponseBodyAsString());
            throw new RuntimeException("Monster not found or API error: " + monsterName);
        } catch (Exception e) {
            logger.error("Unexpected error retrieving monster '{}': {}", monsterName, e.getMessage());
            throw new RuntimeException("Unexpected error retrieving monster: " + monsterName);
        }
    }

    @Override
    public List<String> getMonsterNames(Integer page) {
        try {
            MonsterListDTO response = webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/v1/monsters/")
                            .queryParam("page", page)
                            .queryParam("format", "json")
                            .build())
                    .retrieve()
                    .bodyToMono(MonsterListDTO.class)
                    .timeout(Duration.ofSeconds(5))
                    .block();

            if (response == null || response.getResults() == null) {
                logger.warn("No monsters found for page {}", page);
                throw new RuntimeException("No monsters found for page " + page);
            }

            return response.getResults()
                    .stream()
                    .map(MonsterSummaryDTO::getName)
                    .collect(Collectors.toList());

        } catch (WebClientResponseException e) {
            logger.error("Error retrieving monster names for page {}: {}", page, e.getResponseBodyAsString());
            throw new RuntimeException("Error retrieving monster names for page " + page);
        } catch (Exception e) {
            logger.error("Unexpected error retrieving monster names for page {}: {}", page, e.getMessage());
            throw new RuntimeException("Unexpected error retrieving monster names for page " + page);
        }
    }
}
