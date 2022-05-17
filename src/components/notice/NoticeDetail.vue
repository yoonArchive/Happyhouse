<template>
  <article id="main">
    <header>
      <h2>Notice</h2>
      <p>공지사항 조회</p>
    </header>
    <section class="wrapper style5">
      <div class="inner">
        <h4>Form</h4>
        <form>
          <div class="row gtr-uniform">
            <div class="col-12 col-12-xsmall">
              <input
                type="text"
                name="title"
                id="title"
                placeholder="Enter Title"
                readonly
              />
            </div>
            <div class="col-12">
              <select name="demo-category" id="demo-category">
                <option value="">- Category -</option>
                <option value="1">업데이트</option>
                <option value="1">알림</option>
                <option value="1">버그 개선</option>
                <option value="1">머할까</option>
              </select>
            </div>
            <div class="col-12">
              <textarea
                name="content"
                id="content"
                placeholder="Enter Content"
                rows="10"
                readonly
              ></textarea>
            </div>
            <div class="col-12">
              <ul class="actions">
                <li><button class="primary" @click="save">등록</button></li>
                <li><input type="reset" value="초기화" /></li>
                <li>
                  <button type="button" @click="goList">취소</button>
                </li>
              </ul>
            </div>
          </div>
        </form>
      </div>
    </section>
  </article>
</template>

<script>
export default {
  
};
</script>

<style></style>
