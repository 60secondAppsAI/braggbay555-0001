import http from "../http-common"; 

class ProductCategoryService {
  getAllProductCategorys(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/productCategory/productCategorys`, searchDTO);
  }

  get(productCategoryId) {
    return this.getRequest(`/productCategory/${productCategoryId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/productCategory?field=${matchData}`, null);
  }

  addProductCategory(data) {
    return http.post("/productCategory/addProductCategory", data);
  }

  update(data) {
  	return http.post("/productCategory/updateProductCategory", data);
  }
  
  uploadImage(data,productCategoryId) {
  	return http.postForm("/productCategory/uploadImage/"+productCategoryId, data);
  }




	postRequest(url, data) {
		return http.post(url, data);
      };

	getRequest(url, params) {
        return http.get(url, {
        	params: params
        });
    };

}

export default new ProductCategoryService();
