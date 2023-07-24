package com.payetonkawa.payetonkawa.tag;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tag")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Tag {

	@Id
	private UUID id;

	@Column(length = 30, unique = true, nullable = false)
	private String name;
}
