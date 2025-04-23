package site.amfdev.base.entity;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "movies")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MovieEntity {
	
	@Id
	private Long id;
	
	private String title;
	
	private String poster_path;
	
    @ManyToMany(mappedBy = "movies")
    @JsonBackReference
    private List<MoodEntity> moods = new ArrayList<>();
}
