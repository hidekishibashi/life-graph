package com.lifegraph.team20.payload.request;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
//HTTPリクエストを受け取るためのクラスここに情報の定義が集まっている

@Data
public class LifeGraphdata {
  @NotNull
  private long userId = 1;
  @NotNull
  @Max(99)
  @Min(1)
  private int age = 12;
  @NotNull
  @Max(100)
  @Min(-100)
  private int score = 13;
  @NotNull
  @Size(max = 100, min = 1)
  private String comment = "aaa";
}