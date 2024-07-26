package com.saysimple.supports.service;

import com.saysimple.supports.entity.Support;
import com.saysimple.supports.vo.RequestSupport;
import com.saysimple.supports.vo.RequestUpdateSupport;
import com.saysimple.supports.vo.ResponseSupport;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupportService {
    ResponseSupport create(RequestSupport support);

    List<ResponseSupport> list();

    ResponseSupport get(String supportId);

    ResponseSupport update(RequestUpdateSupport support);

    void delete(String supportId);

    Page<Support> searchByTitle(String title, int page, int size);
}