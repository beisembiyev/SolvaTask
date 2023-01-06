package com.example.solva.Services;

import com.example.solva.Models.DTO.LimitDTO;
import com.example.solva.Models.Entity.Limit;
import com.example.solva.Models.Enums.SpendingsCategory;
import com.example.solva.Repos.LimitRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class LimitService {

    private final LimitRepo limitRepo;

    public LimitDTO getLimit(long id){
        Limit limit=findLimitById(id);
        return LimitDTO.builder()
                .id(limit.getId())
                .spendingsCategory(limit.getSpendingsCategory())
                .amount(limit.getAmount())
                .date(limit.getDate())
                .build();
    }

    public LimitDTO updateLimit(LimitDTO limitDTO){
        Limit limit=findLimitById(limitDTO.getId());
        limit.setAmount(limitDTO.getAmount());
        limit.setDate(limitDTO.getDate());//need to change to LocalDateTime.now() in controller
        limitRepo.save(limit);
        return limitDTO;
    }

    public LimitDTO createLimit(LimitDTO limitDTO){
        Limit limit=Limit.builder()
                //.id(limitDTO.getId())
                .spendingsCategory(limitDTO.getSpendingsCategory())
                .amount(limitDTO.getAmount())
                //.date(limitDTO.getDate()) //need to change to LocalDateTime.now() in controller
                .build();
        limitRepo.save(limit);
        return limitDTO;
    }

    public Limit createInitialProductsLimit(){
        Limit limit=Limit.builder()
                //.id(limitDTO.getId())
                .spendingsCategory(SpendingsCategory.PRODUCTS)
                .amount(0)
                .date(LocalDateTime.now()) //need to change to LocalDateTime.now() in controller
                .build();
        //limitRepo.save(limit);
        return limit;
    }
    public Limit createInitialServicesLimit(){
        Limit limit=Limit.builder()
                //.id(limitDTO.getId())
                .spendingsCategory(SpendingsCategory.SERVICES)
                .amount(0)
                .date(LocalDateTime.now()) //need to change to LocalDateTime.now() in controller
                .build();
        //limitRepo.save(limit);
        return limit;
    }

    public void deleteLimit(long id){
        Limit limit=findLimitById(id);
        limitRepo.delete(limit);
    }

    public List<LimitDTO> getAllLimits(){
        List<Limit> limits=limitRepo.findAll();
        List<LimitDTO> limitDTOS=new ArrayList<>();
        limits.forEach(limit -> limitDTOS.add(LimitDTO.builder()
                .id(limit.getId())
                .spendingsCategory(limit.getSpendingsCategory())
                .amount(limit.getAmount())
                .date(limit.getDate())
                .build()
        ));
        return limitDTOS;
    }

    public Limit findLimitById(long id){
        Limit limit=limitRepo.findLimitById(id);
        if (Objects.isNull(limit)){
            throw new RuntimeException("limit not found!");
        }
        return limit;
    }


}
