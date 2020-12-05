package com.pubsub.demo.subscriber.activemq.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Company.class)
public class Company {
    private String name;

    private List<Product> products;

    public Company() {
    }

    public Company(final String name, final List<Product> products) {
        this.name = name;
        this.products = products;
    }

    // name
    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    // products
    public void setProducts(final List<Product> products) {
        this.products = products;
    }

    public List<Product> getProducts() {
        return this.products;
    }

    /**
     * Show Detail View
     */
    @Override
    public String toString() {
        final JSONObject jsonInfo = new JSONObject();

        try {
            jsonInfo.put("name", this.name);

            final JSONArray productArray = new JSONArray();
            if (this.products != null) {
                this.products.forEach(product -> {
                    final JSONObject subJson = new JSONObject();
                    try {
                        subJson.put("name", product.getName());
                    } catch (final JSONException e) {
                    }

                    productArray.put(subJson);
                });
            }
            jsonInfo.put("products", productArray);
        } catch (final JSONException e1) {
        }
        return jsonInfo.toString();
    }

}