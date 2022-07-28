package fpt.edu.capstone.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name="FOSUser")
@NoArgsConstructor
@AllArgsConstructor
public class FOSUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "fullName")
    private String fullName;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

//    @ManyToOne
//    @JoinColumn(name = "roleId", nullable = true)
//    private Role roleId;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Collection<Role> roles = new ArrayList<>();

    @Column(name = "gender")
    private String gender;

    @Column(name = "contact")
    private String contact;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private Integer status = 1;

    @Column(name = "profileImage")
    private String profileImage;

    @ManyToOne
    @JoinColumn(name = "roleId", nullable = true)
    private Role role;

    public FOSUser(String fullName, String userName, String password, String gender, String contact, String email, Integer status, String profileImage, Role role) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.contact = contact;
        this.email = email;
        this.status = status;
        this.profileImage = profileImage;
        this.role = role;
    }

    public boolean isActive(){
        if(this.status == 1){
            return true;
        }
        return false;
    }
}