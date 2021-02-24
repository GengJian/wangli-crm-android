package customer.entity;

import android.graphics.Color;

import com.wanglicrm.android.R;
import com.bobomee.android.mentions.edit.listener.InsertData;
import com.bobomee.android.mentions.model.FormatRange;

import java.io.Serializable;

/**
 * Resume:
 *
 * @author zhengss
 * @version 1.0
 * @see
 * @since 2017/4/3
 */
public class ClientTag implements Serializable, InsertData {

  private final CharSequence tagLable;
  private final CharSequence tagId;

  private CharSequence tagUrl;

  public ClientTag(CharSequence tagId, CharSequence tagLable) {
    this.tagLable = tagLable;
    this.tagId = tagId;
  }

  public CharSequence getTagLable() {
    return tagLable;
  }

  public CharSequence getTagId() {
    return tagId;
  }

  public CharSequence getTagUrl() {
    return tagUrl;
  }

  public void setTagUrl(CharSequence tagUrl) {
    this.tagUrl = tagUrl;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ClientTag clientTag = (ClientTag) o;

    if (tagLable != null ? !tagLable.equals(clientTag.tagLable) : clientTag.tagLable != null) return false;
    if (tagId != null ? !tagId.equals(clientTag.tagId) : clientTag.tagId != null) return false;
    return tagUrl != null ? tagUrl.equals(clientTag.tagUrl) : clientTag.tagUrl == null;
  }

  @Override
  public int hashCode() {
    int result = tagLable != null ? tagLable.hashCode() : 0;
    result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
    result = 31 * result + (tagUrl != null ? tagUrl.hashCode() : 0);
    return result;
  }

  @Override
  public CharSequence charSequence() {
    return "$" + tagLable + "$";
  }

  @Override
  public FormatRange.FormatData formatData() {
    return new TagConvert(this);
  }

  @Override
  public int color() {
    return R.color.jiuyi_blue;
  }

  private class TagConvert implements FormatRange.FormatData {
    public static final String TAG_FORMAT = "$=%s=%s$";
    private final ClientTag clientTag;

    public TagConvert(ClientTag clientTag) {
      this.clientTag = clientTag;
    }

    @Override
    public CharSequence formatCharSequence() {
      return String.format(TAG_FORMAT, clientTag.getTagId(), clientTag.getTagLable(),
          "$" + clientTag.getTagLable() + "$");
    }
  }
}
