const getters = {
  visitedViews: state => state.tagsView.visitedViews,
  cachedViews: state => state.tagsView.cachedViews,
  userInfo: state => state.user.info,
  userName: state => state.user.name
}
export default getters
