package net.ddns.crbkproject.api;

import org.springframework.data.domain.Page;

import java.util.List;

public class PaginatedResponse<T> {
    private final int number;
    private final int size;
    private final int totalPages;
    private final long totalElements;
    private final List<T> content;

    public PaginatedResponse(List<T> content, Page<?> page) {
        this.number = page.getNumber();
        this.size = page.getSize();
        this.totalPages = page.getTotalPages();
        this.totalElements = page.getTotalElements();
        this.content = content;
    }

    public int getNumber() {
        return number;
    }

    public int getSize() {
        return size;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public List<T> getContent() {
        return content;
    }
}
