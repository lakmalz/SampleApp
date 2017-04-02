package lakmalz.git.sampleapp.services.responsemodels;

/**
 * Created by gayadias on 10/03/2017.
 */

public class UploadPostResponse {
    Result result;
    String error;
    String status;

    public Result getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public String getStatus() {
        return status;
    }

    public class Result {
        int post_id;
        String post_name;
        String post_image;

        public int getPost_id() {
            return post_id;
        }

        public String getPost_name() {
            return post_name;
        }

        public String getPost_image() {
            return post_image;
        }
    }
}
