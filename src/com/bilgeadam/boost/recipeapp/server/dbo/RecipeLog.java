package com.bilgeadam.boost.recipeapp.server.dbo;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "recipe_logs")
@ToString
@Getter
@Setter
@NoArgsConstructor
public class RecipeLog {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long          oid;
	private long          recipeId;
	private String        actionTaken;
	private LocalDateTime actionTimestamp;
}
