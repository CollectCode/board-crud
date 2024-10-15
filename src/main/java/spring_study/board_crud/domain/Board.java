package spring_study.board_crud.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@Table(name = "board")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "title"})
public class Board {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    public Board(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
