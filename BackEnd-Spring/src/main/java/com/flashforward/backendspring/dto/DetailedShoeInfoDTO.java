package com.flashforward.backendspring.dto;

import com.flashforward.backendspring.model.SizeAndQuantity;

import java.util.List;

public record DetailedShoeInfoDTO(int id, String name, String brand, double price, String imgUrl, String detailedInfo
, List<SizeAndQuantity> sizeAndQuantityList) {
}
