<template>
  <header id="header">
    <h1><router-link to="/">Happy House</router-link></h1>
    <nav id="nav">
      <ul>
        <li class="special">
          <a href="#menu" class="menuToggle"><span>Menu</span></a>
          <div id="menu">
            <ul>
              <li><a href="#">Home</a></li>
              <li><a href="#">Generic</a></li>
              <li><a href="#">Elements</a></li>
              <li><a href="#">Notice</a></li>
              <li><a href="#">Sign Up</a></li>
              <li><a href="#">Log In</a></li>
            </ul>
          </div>
        </li>
      </ul>
    </nav>
  </header>
</template>

<script>
export default {};
</script>

<style></style>
