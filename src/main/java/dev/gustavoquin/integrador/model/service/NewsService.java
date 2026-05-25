package dev.gustavoquin.integrador.model.service;

import dev.gustavoquin.integrador.model.entity.News;
import dev.gustavoquin.integrador.model.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> findAll() {
        return newsRepository.findAll();
    }

    public News save(News news) {
        return newsRepository.save(news);
    }

    public News findById(Long id) {
        return newsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Noticia no encontrada con el ID: " + id));
    }

    public void deleteById(Long id) {
        newsRepository.deleteById(id);
    }
}