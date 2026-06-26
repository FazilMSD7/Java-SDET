package api.endpoints;

/*
Swagger URI --> https://petstore.swagger.io

Create User (POST)  : https://petstore.swagger.io/v2/user
Get User (GET)      : https://petstore.swagger.io/v2/user/{username}
Update User (PUT)   : https://petstore.swagger.io/v2/user/{username}
Delete User (DELETE): https://petstore.swagger.io/v2/user/{username}
*/

public class Routes {
	public static String base_url = "https://petstore.swagger.io/v2";
	
	//user module
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{username}";
	public static String update_url = base_url+"/user/{username}";
	public static String delete_url = base_url+"/user/{username}";
	
	//store module
    public static String inventory_url = base_url + "/store/inventory";
    public static String place_order_url = base_url + "/store/order";
    public static String get_order_url = base_url + "/store/order/{orderId}";
    public static String delete_order_url = base_url + "/store/order/{orderId}";
	
	//pet module 
    public static String upload_image_url = base_url + "/pet/{petId}/uploadImage";
    public static String create_pet_url = base_url + "/pet";
    public static String update_pet_url = base_url + "/pet";
    public static String find_pet_by_id_url = base_url + "/pet/{petId}";
    public static String delete_pet_url = base_url + "/pet/{petId}";

}
