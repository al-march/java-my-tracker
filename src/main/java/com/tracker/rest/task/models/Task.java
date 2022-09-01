package com.tracker.rest.task.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tracker.db.entity.TagEntity;
import com.tracker.db.entity.TaskEntity;
import com.tracker.util.Util;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Task {

    Integer id;
    Integer projectId;
    String name;
    String description;
    String link;
    List<TagEntity> tags;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    List<Comment> comments;

    List<Task> children;

    LocalDateTime updatedAt;
    LocalDateTime createdAt;

    public Task(TaskEntity entity) {
        this.id = entity.getId();
        this.projectId = entity.getProject().getId();
        this.name = entity.getName();
        this.description = entity.getDescription();
        this.tags = entity.getTags();
        this.children = Util.entityListToModel(entity.getChildTasks(), Task::new);
        this.link = entity.getProject().getPrefix() + "-" + this.id;

        this.updatedAt = entity.getUpdatedAt();
        this.createdAt = entity.getCreatedAt();
    }
}
