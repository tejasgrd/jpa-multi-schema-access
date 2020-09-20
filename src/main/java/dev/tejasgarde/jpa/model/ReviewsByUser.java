package dev.tejasgarde.jpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewsByUser {
  String name;
  String manufacturer;
  String first_name;
  String last_name;
  String street;
  String city;
  String content;
  Date published_date;
}
