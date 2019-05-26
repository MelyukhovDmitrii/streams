package me.melyukhov.streams.model.dao;

import javax.persistence.*;

@Entity
@Table(name = "translation_keys")
public class TranslationKeys {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "private_keyq")
    private String privateKey;

    @Column(name = "public_keyq")
    private String publicKey;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }
}
