package com.techprimers.kafka.springbootkafkaconsumerexample.Repository;

import com.techprimers.kafka.springbootkafkaconsumerexample.model.es.ProductEs;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends ElasticsearchRepository<ProductEs,Integer> {



}
