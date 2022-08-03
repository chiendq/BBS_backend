package domain.common.valueObjects

case class RawPassword(value: String){
  if(!(value.matches(raw".*\W.*") &&
    value.matches(raw".*[a-zA-Z].*") &&
    value.matches(raw".*[0-9].*"))){
    throw new IllegalArgumentException("Password must have at least 8 characters including letters, numbers and special characters")
  }
}
