package task;

import java.time.LocalDate;

// criando a classe Task
public class Task {

    // deixa como static para ser compartilhado por todas as classes
    private static Integer idCounter = 1;
    private Integer id;
    private String description;
    private TaskEnum status;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    public Task(String description) {
        createdAt = LocalDate.now();
        this.description = description;
        id = idCounter++;
        status = TaskEnum.TODO;
        updatedAt = LocalDate.now();
    }

    public Integer getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(Integer idCounter) {
        this.idCounter = idCounter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TaskEnum getStatus() {
        return status;
    }

    public void setStatus(TaskEnum status) {
        this.status = status;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    

    

}
