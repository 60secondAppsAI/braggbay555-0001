import http from "../http-common"; 

class DisputeService {
  getAllDisputes(searchDTO) {
    console.log(searchDTO)
    return this.getRequest(`/dispute/disputes`, searchDTO);
  }

  get(disputeId) {
    return this.getRequest(`/dispute/${disputeId}`, null);
  }

  findByField(matchData) {
    return this.getRequest(`/dispute?field=${matchData}`, null);
  }

  addDispute(data) {
    return http.post("/dispute/addDispute", data);
  }

  update(data) {
  	return http.post("/dispute/updateDispute", data);
  }
  
  uploadImage(data,disputeId) {
  	return http.postForm("/dispute/uploadImage/"+disputeId, data);
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

export default new DisputeService();
