package com.cloud.lab.management.base;

import org.springframework.http.HttpEntity;

/**
 * ResponseEntity
 * <p></p>
 *
 * @author lxiang
 * @version V1.0
 * @since 17-10-22
 */
public class ResponseEntity<T> extends HttpEntity<T> {

    public ResponseEntity(T body){
        super(body);
    }

}
