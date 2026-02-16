package com.example.intranet.application.ports.out.output;

import java.util.List;

/**
 * Record genérico para representar resultados paginados
 * 
 * @param <T> Tipo dos dados na lista
 * @param data Lista de registros retornados
 * @param page Página atual (0-indexed)
 * @param size Tamanho da página (quantidade de itens por página)
 * @param totalCount Quantidade total de registros encontrados
 */
public record PagedListOutput<T>(
    List<T> data,
    int page,
    int size,
    long totalCount
) {
    /**
     * Calcula o total de páginas baseado no totalCount e size
     */
    public int totalPages() {
        return size > 0 ? (int) Math.ceil((double) totalCount / size) : 0;
    }

    /**
     * Verifica se tem próxima página
     */
    public boolean hasNext() {
        return page < totalPages() - 1;
    }

    /**
     * Verifica se tem página anterior
     */
    public boolean hasPrevious() {
        return page > 0;
    }
}
