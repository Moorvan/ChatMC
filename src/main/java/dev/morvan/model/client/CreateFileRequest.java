package dev.morvan.model.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateFileRequest {
    String dirName;
    String content;
}
