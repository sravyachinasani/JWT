package com.example.polls.repository;

import com.example.polls.payload.JwtAuthenticationResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JwtauthenticationResponseRepository extends JpaRepository<JwtAuthenticationResponse,Long> {


    Optional<JwtAuthenticationResponse> findByAccessToken(String accessToken);

    Boolean existsByAccessToken(String accessToken);
}
