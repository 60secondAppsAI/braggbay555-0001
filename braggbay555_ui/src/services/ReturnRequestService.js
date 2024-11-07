import http from "../http-common"; 

class ReturnRequestService {
  getAllReturnRequests(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/returnRequest/returnRequests`, searchDTO);
  }

  get(returnRequestId) {
    return this.getRequest(`/returnRequest/${returnRequestId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/returnRequest?field=${matchData}`, null);
  }

  addReturnRequest(data) {
    return http.post("/returnRequest/addReturnRequest", data);
  }

  update(data) {
  	return http.post("/returnRequest/updateReturnRequest", data);
  }
  
  uploadImage(data,returnRequestId) {
  	return http.postForm("/returnRequest/uploadImage/"+returnRequestId, data);
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

export default new ReturnRequestService();
