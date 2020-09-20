package dev.tejasgarde.jpa.domain.users;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "addresses",schema = "users")
public class Addresses {
  @Id
  @Column(name = "id", nullable = false)
  private long id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private Users user;
}
