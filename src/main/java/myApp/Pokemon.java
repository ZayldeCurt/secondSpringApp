// tag::sample[]
package myApp;

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
    private int weight;

    protected Pokemon() {}

    public Pokemon(String name, String subname) {
        this.name = name;
        this.subname = subname;
    }

    public Pokemon(String name, String subname, int weight, String speciesName) {
        this.name = name;
        this.subname = subname;
        this.weight = weight;
    }


    @Override
    public String toString() {
        return String.format(
                "Pokemon[id=%d, name='%s', subname='%s', weight=%d]",
                id, name, subname, weight);
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

    public int getWeight() {
        return weight;
    }

}

