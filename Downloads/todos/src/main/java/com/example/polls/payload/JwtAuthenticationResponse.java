package com.example.polls.payload;

import com.example.polls.model.User;

import javax.persistence.*;


@Entity
public class JwtAuthenticationResponse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String accessToken;
    private String tokenType = "Bearer";

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
  private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

   public  JwtAuthenticationResponse()
   {

   }
    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
