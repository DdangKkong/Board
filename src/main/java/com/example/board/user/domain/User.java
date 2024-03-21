package com.example.board.user.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private int id;

  @Column(name = "user_login_id")
  private String userLoginId;

  @Column(name = "password")
  private String password;

  @Column(name = "nickname")
  private String nickname;

  @Column(name = "created_time")
  private LocalDateTime createdTime;

  @Column(name = "updated_time")
  private LocalDateTime updatedTime;

  @Column(name = "removed_time")
  private LocalDateTime removedTime;


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
    auth.add(new SimpleGrantedAuthority("USER"));
    return auth;
  }

  @Override
  public String getUsername() {
    return null;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }
}
