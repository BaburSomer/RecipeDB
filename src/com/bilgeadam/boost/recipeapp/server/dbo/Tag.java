package com.bilgeadam.boost.recipeapp.server.dbo;

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
@Table(name = "tags")
@ToString
@NoArgsConstructor
public class Tag {
	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long   oid;
	@Getter
	@Setter
	private String name;
	@Getter
	@Setter
	private String description;

	public Tag(String name, String description) {
		super();
		this.name        = name;
		this.description = description;
	}

}
