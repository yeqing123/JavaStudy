package com.yeqin.upload;

import lombok.Data;

//封装了上传图片的信息
@Data
public class CFile {
	private String imageUrl;  //上传图片的保存在应用中的名字，例如：/upload/3027db63-62b5-4dce-8187-d8f6595e7d32.jpg
	private String imageName; //保存图片的原始名称
}
