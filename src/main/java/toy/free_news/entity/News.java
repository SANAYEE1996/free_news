package toy.free_news.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "schedule")
@Getter
@NoArgsConstructor
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Column(name = "register_date")
    private String registerDate;

    @Column(name = "modify_date")
    private String modifyDate;

    public News(Long memberId, String title, String text, String registerDate) {
        this.memberId = memberId;
        this.title = title;
        this.text = text;
        this.registerDate = registerDate;
    }
}
