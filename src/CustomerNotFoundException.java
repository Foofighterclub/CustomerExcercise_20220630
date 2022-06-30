public class CustomerNotFoundException extends Exception {

  public CustomerNotFoundException(String customerId) {
    super(String.format("Customer with id %s not found", customerId));
  }
}
