package es.ieslavereda.miravereda.API;
import es.ieslavereda.miravereda.Base.Parameters;
import java.io.IOException;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CallMethods<T>  {
        private Retrofit retrofit = new Retrofit.Builder().baseUrl(Parameters.URL_API_BASE).build();
        private APIService service = retrofit.create(APIService.class);
        private static CallMethods callMethods;

        public static CallMethods getCallMethodsObject(){
            if(callMethods == null){
                callMethods = new CallMethods();
            }
            return callMethods;
        }

        public String get(String url){
            Call<ResponseBody> call = service.getCall(url);
            Response<ResponseBody> response = null;
            try {
                response = call.execute();
                if(response.body()!=null)
                    return response.body().string();
                else if(response.errorBody()!=null)
                    return response.errorBody().string();
                return "No se ha devuelto nada";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String post(String url, RequestBody data){
            Call<ResponseBody> call = service.postCall(url, data);
            Response<ResponseBody> response = null;
            try {
                response = call.execute();
                if(response.body()!=null)
                    return response.body().string();
                else if(response.errorBody()!=null)
                    return response.errorBody().string();
                return "No se ha devuelto nada";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String put(String url, RequestBody data){
            Call<ResponseBody> call = service.putCall(url, data);
            Response<ResponseBody> response = null;
            try {
                response = call.execute();
                if(response.body()!=null)
                    return response.body().string();
                else if(response.errorBody()!=null)
                    return response.errorBody().string();
                return "No se ha devuelto nada";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public String delete(String url){
            Call<ResponseBody> call = service.deleteCall(url);
            Response<ResponseBody> response = null;
            try {
                response = call.execute();
                if(response.body()!=null)
                    return response.body().string();
                else if(response.errorBody()!=null)
                    return response.errorBody().string();
                return "No se ha devuelto nada";
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

