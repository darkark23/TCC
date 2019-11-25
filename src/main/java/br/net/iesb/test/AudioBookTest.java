package br.net.iesb.test;

import br.net.iesb.entity.transacional.AudioLivro;
import br.net.iesb.repository.transacional.AudioLivroRepository;
import br.net.iesb.service.transacional.AudioLivroService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class AudioBookTest {

    @Autowired
    AudioLivroRepository audioLivroService;

    @Test
    public void procurarAudioBook(){

    }

}
