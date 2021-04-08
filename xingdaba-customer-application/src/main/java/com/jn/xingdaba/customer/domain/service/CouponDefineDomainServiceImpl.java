package com.jn.xingdaba.customer.domain.service;

import com.jn.core.builder.KeyBuilder;
import com.jn.xingdaba.customer.domain.model.CouponDefine;
import com.jn.xingdaba.customer.domain.repository.CouponDefineRepository;
import com.jn.xingdaba.customer.infrastructure.exception.CouponNotDefineException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CouponDefineDomainServiceImpl implements CouponDefineDomainService {
    private final CouponDefineRepository repository;
    private final KeyBuilder keyBuilder;

    public CouponDefineDomainServiceImpl(CouponDefineRepository repository,
                                         KeyBuilder keyBuilder) {
        this.repository = repository;
        this.keyBuilder = keyBuilder;
    }

    @Override
    public Page<CouponDefine> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public CouponDefine findByGiveType(String giveType) {
        return repository.findByGiveType(giveType).orElseThrow(CouponNotDefineException::new);
    }

    @Override
    public CouponDefine findMinusCoupon(BigDecimal conditionAmount) {
        return repository.findFirstByGiveTypeAndIsDeleteAndConditionAmountLessThanEqualOrderByConditionAmountDesc("minus", "0", conditionAmount).orElse(null);
    }

    @Override
    public String save(CouponDefine model) {
        if (StringUtils.isEmpty(model.getId())) {
            model.setId(keyBuilder.getUniqueKey());
        }
        if (StringUtils.isEmpty(model.getIsDelete())) {
            model.setIsDelete("0");
        }

        Optional<CouponDefine> oldValue = repository.findById(model.getId());
        if (oldValue.isPresent()) {
            model.setCreateTime(oldValue.get().getCreateTime());
            model.setCreateBy(oldValue.get().getCreateBy());
        }

        return repository.save(model).getId();
    }

    @Override
    public void deleteOrRestore(List<String> ids) {
        repository.saveAll(repository.findAllByIdIn(ids).stream()
                .peek(m -> m.setIsDelete("1".equals(m.getIsDelete()) ? "0" : "1"))
                .collect(Collectors.toList()));
    }

}
