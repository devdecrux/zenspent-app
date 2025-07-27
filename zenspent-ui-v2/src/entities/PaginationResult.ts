export interface PaginationResult<E> {
  content: E[]
  totalPages: number
  totalItems: number
  pageSize: number
  pageNumber: number
  empty: boolean
}
