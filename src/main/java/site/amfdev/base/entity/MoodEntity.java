package site.amfdev.base.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "user_mods")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MoodEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	
	private boolean isPrivate;

	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private UserEntity user;

	@ManyToMany
	@JoinTable(name = "mood_movie", joinColumns = @JoinColumn(name = "mood_id"), inverseJoinColumns = @JoinColumn(name = "movie_id"))
	@JsonManagedReference
	private List<MovieEntity> movies = new ArrayList<>();

}
