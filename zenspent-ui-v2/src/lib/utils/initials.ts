export function initialsFromName(firstName?: string, lastName?: string) {
  const firstInitial = firstName?.trim().charAt(0) ?? ''
  const lastInitial = lastName?.trim().charAt(0) ?? ''
  const initials = `${firstInitial}${lastInitial}`.toUpperCase()
  return initials || '?'
}
