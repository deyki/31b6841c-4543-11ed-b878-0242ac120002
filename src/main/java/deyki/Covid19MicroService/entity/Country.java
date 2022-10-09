package deyki.Covid19MicroService.entity;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "countries")
public class Country {

    @Id
    @SequenceGenerator(name = "country_sequence", sequenceName = "country_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "country_sequence")
    private Long ID;

    @Column(name = "country")
    private String Country;

    @Column(name = "country_code")
    private String CountryCode;

    @Column(name = "slug")
    private String Slug;

    @Column(name = "new_confirmed")
    private Integer NewConfirmed;

    @Column(name = "total_confirmed")
    private Integer TotalConfirmed;

    @Column(name = "new_deaths")
    private Integer NewDeaths;

    @Column(name = "total_deaths")
    private Integer TotalDeaths;

    @Column(name = "new_recovered")
    private Integer NewRecovered;

    @Column(name = "total_recovered")
    private Integer TotalRecovered;

    @Column(name = "date")
    private String Date;

    @Column(name = "premium")
    private String Premium;
}
