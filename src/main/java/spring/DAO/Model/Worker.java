package spring.DAO.Model;

import org.hibernate.annotations.GenericGenerator;
import spring.DAO.Model.Enum.Position;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "worker")
public class Worker implements Serializable {

    private static final long serialVersionUID = 3041583979759312573L;

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name= "increment", strategy= "increment")
    @Column(name = "id", length = 6, nullable = false)
    private Long id;

    @Column(name = "fio")
    private String fio;

    @Column(name = "position")
    @Enumerated(EnumType.STRING)
    private Position position;

    @Column(name = "phone")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "bank")
    private Bank bank;


    public Worker() {
    }

    public Worker(String fio, Position position, String phone,Bank bank) {
        this.fio = fio;
        this.position = position;
        this.phone = phone;
        this.bank = bank;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFio() { return fio; }

    public void setFio(String fio) { this.fio = fio; }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "id=" + id +
                ", fio='" + fio + '\'' +
                ", position=" + position +
                ", phone='" + phone + '\'' +
                ", bank=" + bank.getName() +
                '}';
    }
}
