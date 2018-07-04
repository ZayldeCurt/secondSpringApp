// tag::sample[]
package hello;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String subname;

    protected Pokemon() {}

    public Pokemon(String name, String subname) {
        this.name = name;
        this.subname = subname;
    }

    @Override
    public String toString() {
        return String.format(
                "Pokemon[id=%d, name='%s', subname='%s']",
                id, name, subname);
    }


	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getSubname() {
		return subname;
	}
}

