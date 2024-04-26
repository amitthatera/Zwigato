package com.zwigato.service.identity.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "token", schema = "identity")
public class Token {

    @Id
    @SequenceGenerator(name = "token_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    @Column( name = "token_id")
    private Long tokenId;

    @Column(name = "access_token", unique = true)
    private String accessToken;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Column(name = " validate_at")
    private LocalDateTime validateAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    public Token(){
        super();
    }

    public Token(Long tokenId, String accessToken, LocalDateTime createdAt, LocalDateTime expiresAt,
                 LocalDateTime validateAt, User user) {
        this.tokenId = tokenId;
        this.accessToken = accessToken;
        this.createdAt = createdAt;
        this.expiresAt = expiresAt;
        this.validateAt = validateAt;
        this.user = user;
    }

    public Long getTokenId() {
        return tokenId;
    }

    public void setTokenId(Long tokenId) {
        this.tokenId = tokenId;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public LocalDateTime getValidateAt() {
        return validateAt;
    }

    public void setValidateAt(LocalDateTime validateAt) {
        this.validateAt = validateAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
