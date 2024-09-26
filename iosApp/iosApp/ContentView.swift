import SwiftUI
import shared

struct ContentView: View {
    @State private var launchInfo = "Loading..."

    var body: some View {
        Text(launchInfo)
            .onAppear {
                fetchLaunchData()
            }
    }

    func fetchLaunchData() {
        let spaceXApi = SpaceXApi()
        spaceXApi.fetchNextLaunch { result, error in
            if let launchData = result {
                launchInfo = launchData
            } else if let error = error {
                launchInfo = "Error: \(error.localizedDescription)"
            }
        }
    }
}


struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}