package com.example.board.paging;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagingResponse {

  private List<PagingResponseDto.Response> postList;
  private int pageNo;
  private int pageSize;
  private long totalElements;
  private int totalPages;

}
